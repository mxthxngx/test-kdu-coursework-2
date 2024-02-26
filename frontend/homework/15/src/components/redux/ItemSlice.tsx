import { PayloadAction, createSlice } from "@reduxjs/toolkit"
import { TodoInterface } from "../../TodoInterface"

interface ItemState
{
    itemList: TodoInterface[]
    itemInput: string
    item: string
    strikedItems: TodoInterface[]
}

const initialValue: ItemState = {
    itemList: [],
    itemInput: "",
    item: "",
    strikedItems:[]

}
const itemSlice = createSlice({
    name:'adder',
    initialState:initialValue,
    reducers: {
        setItemList: (state, action: PayloadAction<TodoInterface[]>) => {
            state.itemList = action.payload
        },
        setInputItem: (state, action: PayloadAction<string>) => {
            state.itemInput = action.payload
        },
        setItem:(state, action: PayloadAction<string>) => {
            state.item = action.payload
        },
        setStrikedItem:(state,action: PayloadAction<TodoInterface[]>)=>{
            state.strikedItems = action.payload
        }
    }
})
export const { setItemList, setInputItem ,setItem,setStrikedItem} = itemSlice.actions
export const itemReducer =  itemSlice.reducer;