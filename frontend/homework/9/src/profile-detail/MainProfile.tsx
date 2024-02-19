import React from 'react';
import './MainProfile.css';
import { Heading } from './components/Heading';
import { DataInterface } from './DataInterface';
import { Skills } from './components/Skills';
import { Hobbies } from './components/Hobbies';

interface MainProfileProps {
  readonly apiData: DataInterface;
}

export function MainProfile({ apiData }: MainProfileProps) {
  return (
    <div className="main-container">
      <Heading name={apiData.name} firstname={apiData.firstname} qualification={apiData.qualification} />
      <div className='sub-container'>
        <Skills skills={apiData.skills} />
        <Hobbies hobbies={apiData.hobbies} />
      </div>
    </div>
  );
}
