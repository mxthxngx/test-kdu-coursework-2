import { PayloadAction, createSlice } from "@reduxjs/toolkit";
/**
 * Coding standards: 
 * Slice name: (Name of slice)State
 */
interface CounterState {
    count: number,
    inputState1:number,
    inputState2: number
}
const  initialValue:CounterState = {
    count:0,
    inputState1:0,
    inputState2:0
}
const counterSlice = createSlice({
    name: 'counter',
    initialState:initialValue,
    reducers: {
       increment:(prevState)=>{
        prevState.count += 1
       },
       decrement:(prevState)=>{
        prevState.count -= 1
       },
       incrementBy:(prevState,action:PayloadAction<number>)=>{
        prevState.count += action.payload
       },
       decrementBy:(prevState,action:PayloadAction<number>)=>{
        prevState.count -= action.payload
       },
       setInputState1:(prevState,action:PayloadAction<number>)=>{
        prevState.inputState1 = action.payload
       },
       setInputState2:(prevState,action:PayloadAction<number>)=>{
        prevState.inputState2 = action.payload
       }
    }})

    export const counterReducer = counterSlice.reducer
export const {increment,decrement,incrementBy,decrementBy,setInputState1,setInputState2} = counterSlice.actions