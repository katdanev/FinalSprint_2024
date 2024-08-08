import React from 'react';

const AdditionalTable = ({ data, title, queryType }) => {
    if (!data || data.length === 0) {
        return <p>No data available</p>;
    }

    return (
        <div>
            <h2>{title}</h2>
            <table>
                <thead>
                    {queryType === 'airports-cities' ? (
                        <tr>
                            <th>Airport</th>
                            <th>City</th>
                        </tr>
                    ) : queryType === 'working-airports' ? (
                        <tr>
                            <th>Airport</th>
                        </tr>
                    ) : queryType === 'aircrafts-passengers' ? (
                        <tr>
                            <th>Aircraft</th>
                            <th>Last Flight</th>
                            <th>Passengers</th>
                        </tr>
                    ) : queryType === 'passengers-airports' ? (
                        <tr>
                            <th>Passenger</th>
                            <th>Airports</th>
                        </tr>
                    ) : null}
                </thead>
                <tbody>
                    {queryType === 'airports-cities' ? (
                        data.map((item, index) => (
                            <tr key={index}>
                                <td>{item.airport}</td>
                                <td>{item.city}</td>
                            </tr>
                        ))
                    ) : queryType === 'working-airports' ? (
                        data.map((item, index) => (
                            <tr key={index}>
                                <td>{item.airport}</td>
                            </tr>
                        ))
                    ) : queryType === 'aircrafts-passengers' ? (
                        data.map((item, index) => (
                            <tr key={index}>
                                <td>{item.aircraft}</td>
                                <td>{item.flightDate}</td>
                                <td>{item.passengers}</td>
                            </tr>
                        ))
                    ) : queryType === 'passengers-airports' ? (
                        data.map((item, index) => (
                            <tr key={index}>
                                <td>{item.passenger}</td>
                                <td>{item.airports}</td>
                            </tr>
                        ))
                    ) : null}
                </tbody>
            </table>
        </div>
    );
};

export default AdditionalTable;

