import React from 'react';
import './App.css';
import { DataInterface } from './profile-detail/DataInterface';
import { MainProfile } from './profile-detail/MainProfile';
function App() {
  const apiData : DataInterface = {
    "name": "Amey",
    "firstname": "Amey Aditya",
    "qualification": "SSE",
    "skills": [
        {
            "id": 1,
            "skill": "Python"
        },
        {
            "id": 2,
            "skill": "React"
        }
    ],
    "hobbies": [
        {
            "id": 1,
            "hobby": "Cricket"
        }
    ]
}
  return (
    <MainProfile apiData={apiData} />
  );
}

export default App;
