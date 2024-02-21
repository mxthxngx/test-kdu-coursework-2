import React, { EventHandler } from 'react'
import { APIResponse } from '../ApiResponse'
import './Quote.scss'
interface QuoteProps 
{
    quote: APIResponse,
    setFilterTags?: React.Dispatch<React.SetStateAction<string[]>>,
    filterTags?: string[]

}
export  function Quote({quote,setFilterTags,filterTags}:QuoteProps) {
    const tagClickHandler = (e:React.MouseEvent<HTMLInputElement,MouseEvent>)=>{
        if (setFilterTags) {
            const clickedTag = e.currentTarget.innerText;
            if(!filterTags?.includes(clickedTag)){
                const updatedTags = filterTags ? [...filterTags, clickedTag] : [clickedTag];
                setFilterTags(updatedTags);  
            }
         
        }
    }
  return (
    <div className='quote-container'>
        <h1 className='quote'>{quote.content}</h1>
        <div className='right-align'><p className='author'>~{quote.author}</p>
        <p >{quote.dateAdded.toString()}</p>
        </div>
        <div className='tag-container'>
            {quote.tags.map((tag:string) => (
                <span className="tag" key={tag} onClick={tagClickHandler}>{tag}</span>
            ))}
        </div>
    </div>
  )
}
