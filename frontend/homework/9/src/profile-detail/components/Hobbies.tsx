import React from 'react';
import { HobbiesInterface } from '../DataInterface';
import './Hobbies.css';

interface HobbiesProps {
  readonly hobbies: ReadonlyArray<HobbiesInterface>;
}

export function Hobbies({ hobbies }: HobbiesProps) {
  return (
    <div className='hobbies'>
      <h3>Hobbies</h3>
      <ul>
        {hobbies.map(hobby => <li key={hobby.id}>{hobby.hobby}</li>)}
      </ul>
    </div>
  );
}
