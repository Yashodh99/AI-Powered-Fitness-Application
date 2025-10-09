import {BrowserRouter as Router , Navigate,Route, Routes, useLocation} from "react-router";
import Button from '@mui/material/Button';
import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";


function App() {


  const{token, toeknData, logIn, logOut, isAuthenticated} = useContext(AuthContext);
  const dispatch = useDispatch();

  const[authReady, setAuthReady] = useState(false);
  useEffect(()=> {
    if(token){
      dispatch(setCredentials({token,user:toeknData}));
      setAuthReady(true);

    }
  },[token,toeknData,dispatch]);
  return (
    <Router>

       <Button variant = "cantained" color ="#dc004e">LOGIN</Button>
      </Router>
    
  )
}

export default App
