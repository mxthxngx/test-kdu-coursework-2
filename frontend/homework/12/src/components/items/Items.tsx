import React from 'react';
import "./style.css";
import { ItemsListContext ,ItemInputContext} from '../../components/todoadder/TodoAdder';
export function Items() {
  
  const { itemList,setItemList } = React.useContext(ItemsListContext);
  const { itemInput } = React.useContext(ItemInputContext);
  const handleDelete = (id: number) => {
        setItemList(itemList.filter((item) => item.id !== id))
    }
    let filteredItems = itemList;
    console.log("input: ",itemInput)
    if(itemInput.trim()!=='')
    {
        filteredItems=itemList.filter((item)=> item.todo.toLowerCase().includes(itemInput.toLowerCase()))
        console.log(filteredItems)
    }
    return (
        
    <div className="item-container">
      <h2>Items</h2>
      {filteredItems.length === 0?<ul>No Items found</ul>
      :
      <ul>
      {filteredItems.map((item) => (
        <div className="todo-item" key={item.id}>
          <li>{item.todo}</li>
          <button className='cross-btn' onClick={() => handleDelete(item.id)}>x</button>
        </div>
      ))}
    </ul>
      }
     
    </div>
  )
}
