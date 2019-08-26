import React from 'react';
import { Link } from 'react-router-dom';
export class NavBar extends React.Component {
   render() {
       return (
           <nav className="navbar navbar-expand-lg navbar-light bg-light">
               <Link to="/" className="navbar-brand">Grocery List App</Link>
               <div className="navbar-nav">
                   <Link to="/grocery-lists" className="nav-item nav-link active">Grocery Lists</Link>
               </div>
           </nav>
       )
   }
}