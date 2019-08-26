import { GroceryList } from "./grocery-list";
import { GroceryType } from "./grocery-type";

export class GroceryItem {
    constructor(
        public groceryItemId: number,
        public groceryItemName: string,
        public groceryItemType: GroceryType,
        public groceryItemList: GroceryList
    ){}
}