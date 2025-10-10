import { createSlice } from '@reduxjs/toolkit';

const initialState = { user: null, token: null, loading: false, error: null };

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentiasl:(state,action)=>{
    state.user = action.payload.user;
    state.token = action.payload.token;
    state.userId = action.payload.user.sub;

    localStorage.setItem('token',action.payload.token);
    localStorage.setItem('user',JSON.stringify(action.payload.user));
    localStorage.setItem('userId',action.payload.user.sub);

    },
    
    logout:(state) =>
      { state.user = null; 
        state.token = null; 
        state.userId = null;
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('userId');
      },
  },
});


export const { loginStart, loginSuccess, loginFailure, logout } = authSlice.actions;


export default authSlice.reducer;
