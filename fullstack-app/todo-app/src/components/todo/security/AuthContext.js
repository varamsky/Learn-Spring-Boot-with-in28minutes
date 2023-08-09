import { createContext, useContext, useState } from "react";
import {
  executeBasicAuthenticationService,
  executeJwtAuthenticationService,
} from "../api/AuthenticationApiService";
import { apiClient } from "../api/apiClient";

//1: Create a Context
export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

//2: Share the created context with other components
export default function AuthProvider({ children }) {
  //3: Put some state in the context
  const [isAuthenticated, setAuthenticated] = useState(false);

  const [username, setUsername] = useState(null);

  const [token, setToken] = useState(null);

  // login() with simple authentication
  // function login(username, password) {
  //   if (username === "in28minutes" && password === "dummy") {
  //     setAuthenticated(true);
  //     setUsername(username);
  //     return true;
  //   } else {
  //     setAuthenticated(false);
  //     setUsername(null);
  //     return false;
  //   }
  // }

  // login() with BasicAuth
  // async function login(username, password) {
  //   const baToken = "Basic " + window.btoa(username + ":" + password); // btoa creates a Base64 encoded Basic Authentication token from the username and password
  //   try {
  //     const response = await executeBasicAuthenticationService(baToken);

  //     if (response.status === 200) {
  //       setAuthenticated(true);
  //       setUsername(username);
  //       setToken(baToken);

  //       // Adding the token when it is available. Now, all the axios calls with send this token in the request header.
  //       apiClient.interceptors.request.use((config) => {
  //         console.log("Intercepting and adding a token");
  //         config.headers.Authorization = baToken;
  //         return config;
  //       });

  //       return true;
  //     } else {
  //       logout();
  //       return false;
  //     }
  //   } catch (error) {
  //     logout();
  //     return false;
  //   }
  // }

  // login() with JWT
  async function login(username, password) {
    try {
      const response = await executeJwtAuthenticationService(
        username,
        password
      );

      if (response.status === 200) {
        const jwtToken = `Bearer ${response.data.token}`;
        setAuthenticated(true);
        setUsername(username);
        setToken(jwtToken);

        // Adding the token when it is available. Now, all the axios calls with send this token in the request header.
        apiClient.interceptors.request.use((config) => {
          console.log("Intercepting and adding a token");
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      logout();
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setToken(null);
    setUsername(null);
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, username, token }}
    >
      {children}
    </AuthContext.Provider>
  );
}
