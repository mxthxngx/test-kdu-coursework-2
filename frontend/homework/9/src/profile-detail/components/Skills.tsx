import React from 'react'
import { SkillsInterface } from '../DataInterface'
import './Skills.css'
export function Skills({skills}:{skills:SkillsInterface[]}) {
  return (
    <div className = "primary-container">
        <h3>Skills</h3>
        <ul>
            {skills.map(skill => <li key = {skill.id}>{skill.skill}</li>)}
        </ul>
    </div>
  )
}
