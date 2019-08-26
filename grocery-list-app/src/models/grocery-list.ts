import { GroceryItem } from "./grocery-item";

export class GroceryList {
    constructor(
        public groceryListId: number,
        public groceryListName: string,
        public description: string,
        public items: GroceryItem[]
    ){}
}