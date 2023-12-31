import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { Router, RouterProvider, createBrowserRouter } from "react-router-dom";
import { Login } from "./pages/Login";
import { Register } from "./pages/Register";
import { Landing } from "./pages/Landing";
import { AuthContextProvider } from "./contexts/useAuthContext";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login/>,
  },
  {
    path: "register",
    element: <Register />,
  },
  {
    path: "landing",
    element: <Landing />,
  }
]);

root.render(

    <React.StrictMode>
      <AuthContextProvider>
        <RouterProvider router={router} />
      </AuthContextProvider>
    </React.StrictMode>
 
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
