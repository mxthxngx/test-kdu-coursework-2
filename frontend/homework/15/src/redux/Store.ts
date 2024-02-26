import { configureStore } from "@reduxjs/toolkit";
import { counterReducer } from "./CounterSlice";
export const Store = configureStore({
    reducer:{
        counter:counterReducer // creating slices 
    }
})
export type RootState = ReturnType<typeof Store.getState>;