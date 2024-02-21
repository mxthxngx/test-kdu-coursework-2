import React, { useEffect, useState } from 'react';
import {ReactComponent as Loader} from './assets/loading.svg';
import './App.scss';
import { APIResponse } from './ApiResponse';
import { Quote } from './components/Quote';

function App() {
  const [filterTags, setFilterTags] = useState<string[]>([]);
  const [allQuotes, setAllQuotes] = useState<APIResponse[]>([]);
  const [isLoading,setIsLoading] = useState(false);
  const [quotes, setQuotes] = useState<APIResponse[]>([]);
  useEffect(() => {
    setIsLoading(true);
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((res) => res.json())
      .then((data) => {
        console.log("Data", data);
        setAllQuotes(data);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []); 
  useEffect(() => {
    if(filterTags.length>0)
   {console.log("here") ;
    const filteredQuotes = allQuotes.filter((quote) => {

        return filterTags.every((filtertag) => quote.tags.includes(filtertag));
    });

    setQuotes(filteredQuotes);
  }
  else 
  {
    setQuotes(allQuotes)
  }
}, [filterTags, allQuotes]);


  const onGetQuoteHandler = () => {
    setIsLoading(true);
    fetch("https://api.quotable.io/quotes/random?limit=1")
      .then((res) => res.json())
      .then((data) => {
        console.log("Data", data);
        setAllQuotes([data[0], ...allQuotes]);
        setIsLoading(false)
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }
  const onTagCrossHandler = (tagToRemove:string)=>
  {
    setFilterTags(filterTags.filter((tag) => tag !== tagToRemove))
  }

  return (
    <div className="container">
      <div className='main-container'>
        <div className='title'>
      <div className = "quote-btn">
       
      <button className="get-quote-btn" onClick={()=> onGetQuoteHandler()}>Get Quote</button>
      {isLoading?<Loader className="spinner"/>:null}
      </div>
      <div className="filters-title">
        Filters
       </div>
       
      <div className="filtered-box">
        {filterTags.map((tag, index) => (
          <div className='individual-tag' key={tag+index}>
          <div  className="filtered-tag">{tag}</div>
          <div  className='cross' onClick={() => onTagCrossHandler(tag)}>x</div>
          </div>
        ))}
      </div>
      </div>
      {
        quotes.map((quote) => 
        {
          return <Quote key={quote._id} quote={quote} setFilterTags = {setFilterTags} filterTags={filterTags} />
        })
      }
      </div>
    </div>
  );
}

export default App;
