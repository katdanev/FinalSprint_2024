import React, { useState } from 'react';
import './EditForm.css';

function EditForm({ entity, item, onUpdate, onCancel }) {
    const [formData, setFormData] = useState(item);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onUpdate(formData);
    };

    const renderFormFields = () => {
        switch (entity) {
            case 'Cities':
                return (
                    <>
                        <div className="form-group">
                            <label htmlFor="name">Name:</label>
                            <input
                                type="text"
                                id="name"
                                name="name"
                                value={formData.name || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="country">Country:</label>
                            <input
                                type="text"
                                id="country"
                                name="country"
                                value={formData.country || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="population">Population:</label>
                            <input
                                type="number"
                                id="population"
                                name="population"
                                value={formData.population || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                    </>
                );
            case 'Airports':
                return (
                    <>
                        <div className="form-group">
                            <label htmlFor="name">Name:</label>
                            <input
                                type="text"
                                id="name"
                                name="name"
                                value={formData.name || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="code">Code:</label>
                            <input
                                type="text"
                                id="code"
                                name="code"
                                value={formData.code || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="city_id">City ID:</label>
                            <input
                                type="number"
                                id="city_id"
                                name="city_id"
                                value={formData.city_id || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="isWorkingNow">Is Working Now:</label>
                            <input
                                type="checkbox"
                                id="isWorkingNow"
                                name="isWorkingNow"
                                checked={formData.isWorkingNow || false}
                                onChange={e => handleInputChange({
                                    target: {
                                        name: 'isWorkingNow',
                                        value: e.target.checked
                                    }
                                })}
                            />
                        </div>
                    </>
                );
            case 'Aircrafts':
                return (
                    <>
                        <div className="form-group">
                            <label htmlFor="type">Type:</label>
                            <input
                                type="text"
                                id="type"
                                name="type"
                                value={formData.type || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="airlineName">Airline Name:</label>
                            <input
                                type="text"
                                id="airlineName"
                                name="airlineName"
                                value={formData.airlineName || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="numberOfPassengers">Number of Passengers:</label>
                            <input
                                type="number"
                                id="numberOfPassengers"
                                name="numberOfPassengers"
                                value={formData.numberOfPassengers || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                    </>
                );
            case 'Passengers':
                return (
                    <>
                        <div className="form-group">
                            <label htmlFor="firstName">First Name:</label>
                            <input
                                type="text"
                                id="firstName"
                                name="firstName"
                                value={formData.firstName || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last Name:</label>
                            <input
                                type="text"
                                id="lastName"
                                name="lastName"
                                value={formData.lastName || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="birthDate">Birth Date:</label>
                            <input
                                type="date"
                                id="birthDate"
                                name="birthDate"
                                value={formData.birthDate || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="phoneNumber">Phone Number:</label>
                            <input
                                type="text"
                                id="phoneNumber"
                                name="phoneNumber"
                                value={formData.phoneNumber || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="city_id">City ID:</label>
                            <input
                                type="number"
                                id="city_id"
                                name="city_id"
                                value={formData.city_id || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                    </>
                );
            case 'Flights':
                return (
                    <>
                        <div className="form-group">
                            <label htmlFor="departure_airport_id">Departure Airport ID:</label>
                            <input
                                type="number"
                                id="departure_airport_id"
                                name="departure_airport_id"
                                value={formData.departure_airport_id || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="arrival_airport_id">Arrival Airport ID:</label>
                            <input
                                type="number"
                                id="arrival_airport_id"
                                name="arrival_airport_id"
                                value={formData.arrival_airport_id || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="aircraft_id">Aircraft ID:</label>
                            <input
                                type="number"
                                id="aircraft_id"
                                name="aircraft_id"
                                value={formData.aircraft_id || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="flightDate">Flight Date:</label>
                            <input
                                type="datetime-local"
                                id="flightDate"
                                name="flightDate"
                                value={formData.flightDate || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="passengers_ids">Passengers IDs:</label>
                            <textarea
                                id="passengers_ids"
                                name="passengers_ids"
                                value={formData.passengers_ids || ''}
                                onChange={handleInputChange}
                            />
                        </div>
                    </>
                );
            default:
                return null;
        }
    };

    return (
        <form onSubmit={handleSubmit} className="edit-form">
            <h2 className="header">Edit {entity}</h2>
            {renderFormFields()}
            <div className="form-group">
                <button type="submit">Update</button>
                <button type="button" onClick={onCancel}>Cancel</button>
            </div>
        </form>
    );
}

export default EditForm;
