import React, { useState } from 'react';
import './EditDeleteButtons.css'
import EditForm from '../EditForm/EditForm';

function EditDeleteButtons({onEdit, onDelete, recordID}){

    return(
        <div>
            
            <button className="edit-btn" onClick={()=>onEdit(recordID)}>
                <img src="edit-img.png" alt="edit-img"></img>
            </button>

            <button className="delete-btn" onClick={()=>onDelete(recordID)}>
                <img src="delete-img.png" alt="delete-img"></img>
            </button>
        </div>
    );
}

export default EditDeleteButtons;