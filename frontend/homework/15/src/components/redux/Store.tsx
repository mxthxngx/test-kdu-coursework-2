import { configureStore } from "@reduxjs/toolkit";
import { itemReducer } from "./ItemSlice";

export const Store = configureStore({
    reducer:{
        adder: itemReducer
    }
})
export type RootState=ReturnType<typeof Store.getState>;