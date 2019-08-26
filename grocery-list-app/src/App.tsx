import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { NavBar } from './components/navbar/navbar.component';
import { GroceryListView } from './components/grocery-lists/grocery-lists.components';
import { SingleGroceryListView } from './components/single-list/single-list.component';

const App: React.FC = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <NavBar />
        <Switch>
          <Route path="/grocery-lists/:listId" component={SingleGroceryListView} />
          <Route path="/grocery-lists" component={GroceryListView} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
