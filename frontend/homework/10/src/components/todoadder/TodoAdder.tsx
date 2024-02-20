import React, { useState } from 'react';
import './style.css';
import { Items } from '../items/Items';
import { TodoInterface } from '../../TodoInterface';
import { Header } from '../header/Header';

export function TodoAdder() {
  const [item, setItem] = useState(""); 
  const [itemList, setItemList] = useState<TodoInterface[]>([]);
  const [itemtInput,setInputItem] = useState("");
  const addItem = () => {
    if (item !== "") {
      setItem(""); 
      setItemList([...itemList, { id: Date.now(), todo: item, isDone: false }])
    }
  }

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setItem(event.target.value); 
  }

  return (
    <div className='main-container'>
      <Header  setInputItem={setInputItem}/>
      <div className = "body">
    <div className="todo-header">
      <h2>Add Items</h2>
      <div className="todo-input">
        <input type="text" value={item} onChange={handleInputChange} />
        <button className="submit-btn" onClick={addItem}>Submit</button>
      </div>
         <Items itemList={itemList} setItemList={setItemList} itemInput={itemtInput}/>

    </div>
    </div>
    </div>
  );
}
