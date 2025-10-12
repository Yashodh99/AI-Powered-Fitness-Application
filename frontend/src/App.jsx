// import {BrowserRouter as Router , Navigate,Route, Routes, useLocation} from "react-router";
// import Button from '@mui/material/Button';
// import { useContext } from "react";
// import { useEffect } from "react";
// import { useState } from "react";
// import {AuthContext} from 'react-oauth2-code-pkce';
// import {useDispatch} from 'react-redux';
// import {setCredentials} from './store/authSlice';
// import Box from "@mui/material/Box";
// import ActivityForm from "./components/ActivityForm";
// import ActivityList from "./components/ActivityList";
// import ActivityDetail from "./components/ActivityDetail";





// const ActivitiesPage = () => {
//   return(
//   <Box sx={{p:2, border:'1pz dashed grey'}}>
//   <ActivityForm onActivitiesAdded = {()=> window.location.reload()} />
//   <ActivityList />
//   </Box>);
// }

// function App() {


//   const{token, toeknData, logIn, logOut, isAuthenticated} = useContext(AuthContext);
//   const dispatch = useDispatch();

//   const[authReady, setAuthReady] = useState(false);
//   useEffect(()=> {
//     if(token){
//       dispatch(setCredentials({token,user:toeknData}));
//       setAuthReady(true);

//     }
//   },[token,toeknData,dispatch]);
//   return (
//     <Router>
//       {!token ? (
//        <Button variant = "cantained" color ="#dc004e"
//        onClick={() =>{
//         logIn();
//        }}> LOGIN </Button>
//       ):(
       

//          <Box sx={{ p: 2, border: '1px dashed grey' }}>
//           <Button variant = "contained" color ="secondary"
//        onClick={logOut}>
       
//         Logout </Button>
//       <Routes>
//         <Route path="/activities" element={<ActivitiesPage/>}/>
//         <Route path="/activities/:id" element={<ActivityDetail/>}/>

//         <Route path ="/" element={token ? <Navigate to="/activities" replace/> : <div>Welcome Please Login.</div>} />
//       </Routes>
//     </Box>

//       )}
//     </Router>
    
//   )
// }

// export default App



import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router";
import Button from "@mui/material/Button";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { setCredentials } from "./store/authSlice";
import Box from "@mui/material/Box";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

const ActivitiesPage = () => {
  return (
    <Box sx={{ p: 2, border: "1px dashed grey" }}>
      <ActivityForm onActivityAdded={() => window.location.reload()} />
      <ActivityList />
    </Box>
  );
};

function App() {
 
  const { token, tokenData, login, logoff, isAuthenticated } = useContext(AuthContext);
  const dispatch = useDispatch();

  const [authReady, setAuthReady] = useState(false);

  useEffect(() => {
    if (token) {
      dispatch(setCredentials({ token, user: tokenData }));
      setAuthReady(true);
    }
  }, [token, tokenData, dispatch]);

  return (
    <Router>
      {!token ? (
        <Button
          variant="contained"    
          color="secondary"      
          onClick={() => {
            login();              
          }}
        >
          LOGIN
        </Button>
      ) : (
        <Box sx={{ p: 2, border: "1px dashed grey" }}>
          <Button
            variant="contained"
            color="secondary"
            onClick={logoff}      
          >
            Logout
          </Button>

          <Routes>
            <Route path="/activities" element={<ActivitiesPage />} />
            <Route path="/activities/:id" element={<ActivityDetail />} />
            <Route
              path="/"
              element={token ? <Navigate to="/activities" replace /> : <div>Welcome Please Login.</div>}
            />
          </Routes>
        </Box>
      )}
    </Router>
  );
}

export default App;
