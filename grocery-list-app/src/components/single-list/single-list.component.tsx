import React from 'react';
import { Input, Button, Table, Label, Form, Card, CardHeader, CardBody } from 'reactstrap';
import { RouteComponentProps } from 'react-router';
import { GroceryType } from '../../models/grocery-type';
import { GroceryList } from '../../models/grocery-list';
import { GroceryItem } from '../../models/grocery-item';

interface RouteParam{
    listId: any
}

interface IState {
    list?: GroceryList,
    types: GroceryType[],
    items: GroceryItem[],
    groceryItemName: string,
    errorMessage: string,
    errorCreateMessage: string,
    successMessage: string,
    selectedType: number
}

export class SingleGroceryListView extends React.Component<RouteComponentProps<RouteParam>, IState> {
    constructor(props: any) {
        super(props);
        this.state = {
            types: [],
            items: [],
            groceryItemName: '',
            errorMessage: '',
            errorCreateMessage: '',
            successMessage: '',
            selectedType: 0
        }
        const {listId} = this.props.match.params
        this.getListById(listId);
        this.getTypes();
        this.getItems();
        this.getItems = this.getItems.bind(this);
        this.getListById = this.getListById.bind(this);
        this.handleDropdown = this.handleDropdown.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        const {listId} = this.props.match.params
        this.getListById(listId);
        this.getItems();
    }

    getItems = async () => {
        const resp = await fetch('http://localhost:8012/grocery-items/no-list', {
            credentials: 'include'
        })

        const items = await resp.json();

        this.setState({
            ...this.state,
            items
        })
    }

    getTypes = async () => {
        const resp = await fetch('http://localhost:8012/types', {
            credentials: 'include'
        })

        const types = await resp.json();

        this.setState({
            ...this.state,
            types
        })
    }

    getListById = async(listId: any) => {
        const resp = await fetch('http://localhost:8012/grocery-lists/' + listId, {
            credentials: 'include'
        })
        const list = await resp.json();

        this.setState({
            ...this.state,
            list
        })
    }


    handleChange = (event: any) => {
        this.setState({
            ...this.state,
            [event.target.name]: event.target.value
        })
    }


    submit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const {listId} = this.props.match.params
        const newItem = {
            groceryItemName: this.state.groceryItemName, 
            groceryItemType: {
                groceryTypeId: this.state.selectedType
            },
            groceryItemList: {
                groceryListId: listId
            }
        }
        const resp = await fetch('http://localhost:8012/grocery-items', {
            credentials: 'include',
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(newItem)
        })
        
        const item = await resp.json();

        if(!item) {

        }else {
            this.setState({
                ...this.state,
                groceryItemName: '',
                selectedType: 0
            })
            this.getListById(listId);
        }

    }

    handleDropdown (event: any) {
        this.setState({
            ...this.state,
            selectedType: event.target.value
        })
    }

    removeItem= async (itemId: any) => {
        const {listId} = this.props.match.params
        const resp = await fetch(`http://localhost:8012/grocery-lists/${listId}/items/${itemId}`, {
            credentials: 'include',
            method:'DELETE'
        })

        const newList = await resp.json();

        if(!newList){

        }else {
            this.setState({
                ...this.state,
                list: newList
            })
            this.getItems();
        }
    }

    addItem = async (itemId: any) => {
        const {listId} = this.props.match.params
        const item ={
            groceryItemId: itemId
        }
        const resp = await fetch(`http://localhost:8012/grocery-lists/${listId}/items`,{
            credentials: 'include',
            body: JSON.stringify(item),
            headers: {
                'content-type': 'application/json'
            },
            method: 'POST'
        })

        const list = await resp.json();

        if(!list) {
            
        }else{
            this.getListById(listId);
            this.getItems();
        }
    }

    render() {
        const errorMessage = this.state.errorMessage;
        const types = this.state.types;
        const list = this.state.list;
        const items = this.state.items;
        return (
            <div className="list-view">
                <h2>List name: {list && list.groceryListName}</h2>
                <p className="error-message">{errorMessage}</p>
                <Table>
                    <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Item Type</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            list && list.items.map(item =>
                                <tr key={item.groceryItemId}>
                                    <td>{item.groceryItemName}</td>
                                    <td>{item.groceryItemType.groceryTypeName}</td>
                                    <td className="delete-item"><Button size="sm" color="danger" onClick={()=> this.removeItem(item.groceryItemId)}>Remove</Button></td>
                                </tr>
                            )
                        }
                    </tbody>
                </Table>

                <div className="create-new-list">
                    <Card>
                        <CardHeader tag="h3">Create a new item</CardHeader>
                        <CardBody>
                            <Form onSubmit={this.submit}>
                                <p className="success-message">{this.state.successMessage}</p>
                                <p className="error-message">{this.state.errorCreateMessage}</p>
                                <Label>Item name:</Label>
                                <Input name="groceryItemName" type="text" value={this.state.groceryItemName} onChange={this.handleChange}></Input>
                                <Label>Type:</Label>
                                <Input type="select" value={this.state.selectedType} onChange={this.handleDropdown}>
                                    <option value={0}>Select</option>
                                    {
                                        types && types.map(type =>
                                            <option key={type.groceryTypeId} value={type.groceryTypeId}>{type.groceryTypeName}</option>
                                        )
                                    }

                                </Input>
                                <Button className="submit-button" color="success" type="submit">Create</Button>
                            </Form>
                        </CardBody>
                    </Card>
                </div>
                <br/>                   
                <Table>
                    <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Item Type</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            items && items.map(item =>
                                <tr key={item.groceryItemId}>
                                    <td>{item.groceryItemName}</td>
                                    <td>{item.groceryItemType.groceryTypeName}</td>
                                    <td className="delete-item"><Button size="sm" color="success" onClick={()=> this.addItem(item.groceryItemId)}>+Add</Button></td>
                                </tr>
                            )
                        }
                    </tbody>
                </Table>
            </div>
        )
    }

}