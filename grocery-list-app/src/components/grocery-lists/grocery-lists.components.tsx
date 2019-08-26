import React from 'react';
import { GroceryList } from '../../models/grocery-list';
import { Input, Button, Table, Pagination, PaginationItem, PaginationLink, Label, Form, Card, CardHeader, CardBody } from 'reactstrap';
import { RouteComponentProps } from 'react-router';

interface IState {
    lists: GroceryList[]
    groceryListName: string,
    description: string,
    totalPages: number,
    limit: number,
    currentPage: number,
    errorMessage: string,
    findName: string,
    errorCreateMessage: string,
    successMessage: string
}

export class GroceryListView extends React.Component<RouteComponentProps, IState> {
    constructor(props: any) {
        super(props);
        this.state = {
            lists: [],
            groceryListName: '',
            description: '',
            totalPages: 0,
            limit: 5,
            currentPage: 1,
            errorMessage: '',
            findName: '',
            errorCreateMessage: '',
            successMessage: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleDropdown = this.handleDropdown.bind(this);
    }

    componentDidMount() {
        this.loadAllList(this.state.currentPage, this.state.limit);
    }

    handleDropdown(event: any) {
        this.setState({
            ...this.state,
            limit: event.target.value
        })
    }

    componentDidUpdate(prevProps: any, prevState: any) {
        if (this.state.limit !== prevState.limit) {
            this.loadAllList(this.state.currentPage, this.state.limit);
        }
    }

    handleChange = (event: any) => {
        this.setState({
            ...this.state,
            [event.target.name]: event.target.value
        })
    }

    findByName = async () => {
        const name = this.state.findName
        if (!name) {
            this.loadAllList(this.state.currentPage, this.state.limit);
        } else {
            const resp = await fetch('http://localhost:8012/grocery-lists/name/' + name, {
                credentials: 'include'
            })

            const lists = await resp.json();

            if (lists.length === 0) {
                this.setState({
                    ...this.state,
                    lists: [],
                    errorMessage: 'No list found!',
                    totalPages: 0,
                    currentPage: 1,
                    limit: 5
                })
            } else {
                this.setState({
                    ...this.state,
                    errorMessage: '',
                    lists,
                    totalPages: 0,
                    currentPage: 1,
                    limit: 5
                })
            }
        }


    }

    loadAllList = async (currentPage: number, limit: number) => {
        const resp = await fetch(`http://localhost:8012/grocery-lists?page=${currentPage - 1}&limit=${limit}`, {
            credentials: 'include'
        })

        const lists = await resp.json();

        if (!lists.content) {
            this.setState({
                ...this.state,
                lists: [],
                errorMessage: 'No list found!'
            })
        } else {
            this.setState({
                ...this.state,
                errorMessage: '',
                lists: lists.content,
                totalPages: lists.totalPages,
                currentPage,
                limit
            })
        }
    }

    submit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const newList = {
            groceryListName: this.state.groceryListName,
            description: this.state.description
        }
        const resp = await fetch('http://localhost:8012/grocery-lists', {
            credentials: 'include',
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(newList)
        })
        const list = await resp.json();
        if (!list) {
            this.setState({
                ...this.state,
                successMessage: '',
                errorCreateMessage: 'Could not create new list!'
            })
        } else {
            this.setState({
                ...this.state,
                successMessage: 'List created with ID: ' + list.groceryListId,
                errorCreateMessage: '',
                groceryListName: '',
                description: ''
            })
            this.loadAllList(this.state.currentPage, this.state.limit);
        }
    }

    goToList(listId: number) {
        this.props.history.push('/grocery-lists/' + listId);
    }

    render() {
        const lists = this.state.lists;
        const errorMessage = this.state.errorMessage;
        const totalPages = this.state.totalPages;
        const currentPage = this.state.currentPage;
        const limit = this.state.limit;
        return (
            <div className="list-view">
                <div className="search-list-name">
                    <Input
                        type="text"
                        name="findName"
                        value={this.state.findName}
                        onChange={this.handleChange}
                        placeholder="Find by list name"
                    />
                    {this.state.findName ?
                        <Button color="primary" onClick={this.findByName}>Find</Button>
                        : <Button color="primary" onClick={this.findByName}>All</Button>
                    }
                </div>
                <p className="error-message">{errorMessage}</p>
                <Table>
                    <thead>
                        <tr>
                            <th>List Name</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            lists && lists.map(list =>
                                <tr key={list.groceryListId} onClick={() => this.goToList(list.groceryListId)}>
                                    <td>{list.groceryListName}</td>
                                    <td>{list.description}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </Table>
                <Input className="limit-option" type="select" value={this.state.limit} onChange={this.handleDropdown}>
                    <option value={5}>5</option>
                    <option value={10}>10</option>
                    <option value={15}>15</option>
                </Input>
                {lists && <div hidden={(totalPages === 1 || totalPages === 0)}>
                    <Pagination>
                        <PaginationItem disabled={currentPage === 1}>
                            <PaginationLink onClick={e => this.loadAllList(1, limit)} first />
                        </PaginationItem>
                        <PaginationItem disabled={currentPage === 1}>
                            <PaginationLink previous onClick={e => this.loadAllList(currentPage - 1, limit)} />
                        </PaginationItem>

                        <PaginationItem>
                            <PaginationLink>
                                {currentPage} of {totalPages}
                            </PaginationLink>
                        </PaginationItem>

                        <PaginationItem disabled={currentPage === totalPages}>
                            <PaginationLink next onClick={e => this.loadAllList(currentPage + 1, limit)} />
                        </PaginationItem>
                        <PaginationItem disabled={currentPage === totalPages}>
                            <PaginationLink last onClick={e => this.loadAllList(totalPages, limit)} />
                        </PaginationItem>
                    </Pagination>
                </div>}
                <div className="create-new-list">
                    <Card>
                        <CardHeader tag="h3">Create a new list</CardHeader>
                        <CardBody>
                            <Form onSubmit={this.submit}>
                                <p className="success-message">{this.state.successMessage}</p>
                                <p className="error-message">{this.state.errorCreateMessage}</p>
                                <Label>List name:</Label>
                                <Input name="groceryListName" type="text" value={this.state.groceryListName} onChange={this.handleChange}></Input>
                                <Label>Description:</Label>
                                <Input name="description" type="textarea" rows="3" value={this.state.description} onChange={this.handleChange}></Input>
                                <Button className="submit-button" color="success" type="submit">Create</Button>
                            </Form>
                        </CardBody>
                    </Card>
                </div>
            </div>
        )
    }

}