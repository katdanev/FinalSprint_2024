import React, { useState } from "react";
import "./App.css";
import EditDeleteButtons from "./Components/EditDeleteButtons/EditDeleteButtons";
import EditForm from "./Components/EditForm/EditForm";
import CreateForm from "./Components/CreateForm/CreateForm"; // Додайте цей імпорт
import AdditionalTable from "./Components/AdditionalTable";

function App() {
  const [selectedEntity, setSelectedEntity] = useState("");
  const [entityData, setEntityData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [showEditProduct, setShowEditProduct] = useState(null);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const [additionalData, setAdditionalData] = useState([]);
  const [additionalDataTitle, setAdditionalDataTitle] = useState("");
  const [queryType, setQueryType] = useState("");

  const entities = ["Cities", "Airports", "Aircrafts", "Flights", "Passengers"];

  const handleSelectChange = (event) => {
    setSelectedEntity(event.target.value);
  };

  // read data from DB
  const handleFetchData = async () => {
    if (!selectedEntity) return;

    setLoading(true);
    try {
      const entityLowerCase = selectedEntity.toLowerCase();
      const response = await fetch(
        `http://localhost:8080/api/${entityLowerCase}/all`
      );
      const data = await response.json();
      setEntityData(data);
    } catch (error) {
      console.error("Error fetching entity data:", error);
    } finally {
      setLoading(false);
    }
  };

  // delete record
  const handleDeleteClick = (id) => {
    const confirmed = window.confirm(
      "Do you really want to delete this record?"
    );
    if (!confirmed) return;

    const requestOptions = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      mode: "cors",
    };

    console.log("Deleting item with id:", id);

    fetch(
      `http://localhost:8080/api/${selectedEntity.toLowerCase()}/delete/${id}`,
      requestOptions
    )
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`Error ${response.status}: ${text}`);
          });
        }
        setEntityData(entityData.filter((item) => item.id !== id));
        console.log("Item deleted successfully");
      })
      .catch((error) => {
        console.error("Error deleting item:", error.message);
      });
  };

  // edit record
  const handleEditClick = (item) => {
    const { id, ...editableData } = item;
    setShowEditProduct({ id, editableData });
  };

  // update record
  const handleUpdate = (item) => {
    const confirmed = window.confirm(
      "Do you really want to update this record?"
    );
    if (!confirmed) return;

    const id = Object.values(item)[0];

    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(item),
      mode: "cors",
    };

    console.log("Updating item with id:", id);

    fetch(
      `http://localhost:8080/api/${selectedEntity.toLowerCase()}/update/${id}`,
      requestOptions
    )
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`Error ${response.status}: ${text}`);
          });
        }
        return response.json();
      })
      .then((data) => {
        setEntityData((prevData) =>
          prevData.map((existingItem) =>
            existingItem.id === id ? data : existingItem
          )
        );
        setShowEditProduct(null);
        console.log("Item updated successfully");
      })
      .catch((error) => {
        console.error("Error updating item:", error.message);
      });
  };

  // create record
  const handleCreate = (newItem) => {
    const confirmed = window.confirm(
      "Do you really want to create this record?"
    );
    if (!confirmed) return;

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newItem),
      mode: "cors",
    };

    console.log("Creating new item:", newItem);

    fetch(
      `http://localhost:8080/api/${selectedEntity.toLowerCase()}/create`,
      requestOptions
    )
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(`Error ${response.status}: ${text}`);
          });
        }
        return response.json();
      })
      .then((data) => {
        setEntityData((prevData) => [...prevData, data]);
        setShowCreateForm(false);
        console.log("Item created successfully");
      })
      .catch((error) => {
        console.error("Error creating item:", error.message);
      });
  };

  // question 1
  const fetchAirportsAndCities = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        `http://localhost:8080/api/airports/with-cities`
      );
      const data = await response.json();

      const formattedData = Object.values(data).map((item) => {
        const [airport, city] = item.split(" - ");
        return { airport, city };
      });

      console.log("Formatted Airports and Cities data:", formattedData);
      setAdditionalData(formattedData);
      setAdditionalDataTitle("Airports and Cities");
      setQueryType("airports-cities");
    } catch (error) {
      console.error("Error fetching Airports and Cities data:", error);
    } finally {
      setLoading(false);
    }
  };

  // question 2
  const fetchAircraftsAndPassengers = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/aircrafts/with-passengers"
      );
      const data = await response.json();

      const formattedData = data.map((item) => {
        const [aircraftAndFlight, passengersPart] =
          item.split(", Passengers: ");
        const [aircraft, flightDate] = aircraftAndFlight.split(" - ");
        const passengers = passengersPart || "";
        return {
          aircraft,
          flightDate,
          passengers,
        };
      });

      console.log("Formatted Aircrafts and Passengers data:", formattedData);

      setAdditionalData(formattedData);
      setAdditionalDataTitle("Aircrafts and Passengers");
      setQueryType("aircrafts-passengers");
    } catch (error) {
      console.error("Error fetching aircrafts and passengers data:", error);
    } finally {
      setLoading(false);
    }
  };

  // question 3
  const fetchWorkingAirports = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/airports/which-is-working"
      );
      const data = await response.json();

      const formattedData = data.map((airport) => ({ airport }));

      console.log("Formatted Working Airports data:", formattedData);

      setAdditionalData(formattedData);
      setAdditionalDataTitle("Working Airports");
      setQueryType("working-airports");
    } catch (error) {
      console.error("Error fetching working airports data:", error);
    } finally {
      setLoading(false);
    }
  };

  // question 4
  const fetchPassengersAndAirports = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/passengers/used-airports"
      );
      const data = await response.json();

      const formattedData = Object.entries(data).map(
        ([passenger, airports]) => ({
          passenger,
          airports: airports.join(", "),
        })
      );

      console.log("Formatted data:", formattedData);
      setAdditionalData(formattedData);
      setAdditionalDataTitle("Passengers and Airports");
      setQueryType("passengers-airports");
    } catch (error) {
      console.error("Error fetching passengers and airports data:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Entities</h1>
      <select value={selectedEntity} onChange={handleSelectChange}>
        <option value="" disabled>
          Select an entity
        </option>
        {entities.map((entity, index) => (
          <option key={index} value={entity}>
            {entity}
          </option>
        ))}
      </select>
      <button onClick={handleFetchData} disabled={!selectedEntity}>
        Output data
      </button>
      <button
        onClick={() => setShowCreateForm(true)}
        disabled={!selectedEntity}
      >
        Create record
      </button>
      <button onClick={fetchAirportsAndCities}>Airports and Cities</button>
      <button onClick={fetchAircraftsAndPassengers}>
        Aircrafts and Passengers
      </button>
      <button onClick={fetchWorkingAirports}>Working Airports</button>
      <button onClick={fetchPassengersAndAirports}>
        Passengers and Airports
      </button>

      {loading && <p>Loading...</p>}

      {entityData.length > 0 && (
        <table>
          <thead>
            <tr>
              {Object.keys(entityData[0]).map((key) => (
                <th key={key}>{key}</th>
              ))}
              <th>Operations</th>
            </tr>
          </thead>
          <tbody>
            {entityData.map((item, index) => (
              <tr key={index}>
                {Object.keys(item).map((key, idx) => (
                  <td key={idx}>{String(item[key])}</td>
                ))}
                <td>
                  <EditDeleteButtons
                    onEdit={() => handleEditClick(item)}
                    onDelete={() => handleDeleteClick(Object.values(item)[0])}
                  />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showEditProduct && (
        <EditForm
          entity={selectedEntity}
          item={showEditProduct.editableData}
          id={showEditProduct.id}
          onUpdate={(updatedItem) =>
            handleUpdate({ ...updatedItem, id: showEditProduct.id })
          }
          onCancel={() => setShowEditProduct(null)}
        />
      )}
      {showCreateForm && (
        <CreateForm
          entity={selectedEntity}
          onCreate={handleCreate}
          onCancel={() => setShowCreateForm(false)}
        />
      )}

      {additionalData.length > 0 && (
        <AdditionalTable
          data={additionalData}
          title={additionalDataTitle}
          queryType={queryType}
        />
      )}
    </div>
  );
}

export default App;
