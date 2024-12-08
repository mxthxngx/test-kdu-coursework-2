import { createSlice, PayloadAction } from '@reduxjs/toolkit';


export interface UserState {
    user: string;
    loading: boolean;
    error: string | null;
}

const initialState: UserState = {
    user: "",
    loading: false,
    error: null,
};
export const userSlice = createSlice({
    name:'user',
    initialState,
    reducers:{
        setUser: (state, action: PayloadAction<string>) => {
            state.user = action.payload;
            state.loading = false;
            state.error = null;
        },
        setLoading: (state, action: PayloadAction<boolean>) => {
            state.loading = action.payload;
        },
        setError: (state, action: PayloadAction<string>) => {
            state.error = action.payload;
            state.loading = false;
        },
        clearUser: (state) => {
            state.user = "";
            state.loading = false;
            state.error = null;
        },
    },
})
export const {setUser} = userSlice.actions;
export default userSlice.reducer;