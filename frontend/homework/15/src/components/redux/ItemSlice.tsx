import { PayloadAction, createSlice } from "@reduxjs/toolkit"
import { TodoInterface } from "../../TodoInterface"

interface ItemState
{
    itemList: TodoInterface[]
    itemInput: string
    item: string
}

const initialValue: ItemState = {
    itemList: [],
    itemInput: "",
    item: ""

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
        }
    }
})
export const { setItemList, setInputItem ,setItem} = itemSlice.actions
export const itemReducer =  itemSlice.reducer;