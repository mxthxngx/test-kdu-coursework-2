import { Tuple, configureStore } from "@reduxjs/toolkit";
import { itemReducer } from "./ItemSlice";
import { persistReducer, persistStore } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import {thunk} from 'redux-thunk'

const persistConfig = {
    key:'root',
    storage
}
const persistedReducer = persistReducer(persistConfig, itemReducer)
export const Store = configureStore({
    reducer:{
        adder: persistedReducer,
    },
    middleware: () => new Tuple(thunk)
});
export const persistor = persistStore(Store)
export type RootState=ReturnType<typeof Store.getState>;