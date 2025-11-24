import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [searchQuery, setSearchQuery] = useState('');
  const [employees, setEmployees] = useState([]);
  const [summary, setSummary] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    if (!searchQuery.trim()) return;
    
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/api/employees/search?q=${encodeURIComponent(searchQuery)}`
      );
      setEmployees(response.data.employees || []);
      setSummary(response.data.summary || '');
    } catch (error) {
      console.error('Error searching employees:', error);
      alert('Failed to search employees. Make sure the backend is running on port 8080.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">      <header className="App-header">
        <h1>🔍 RAG Employee Search</h1>
        <p>AI-powered employee search using RAG (Retrieval Augmented Generation)</p>
      </header>

      <div className="search-container">
        <input
          type="text"
          className="search-input"
          placeholder="Search employees... (e.g., 'Python developers with 5+ years')"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyPress={(e) => e.key === 'Enter' && handleSearch()}
        />
        <button className="search-button" onClick={handleSearch} disabled={loading}>
          {loading ? 'Searching...' : 'Search'}
        </button>
      </div>

      <div className="examples">
        <span>Try:</span>
        <button className="example-btn" onClick={() => { setSearchQuery('Python developers'); handleSearch(); }}>Python developers</button>
        <button className="example-btn" onClick={() => { setSearchQuery('React 5+ years'); handleSearch(); }}>React 5+ years</button>
        <button className="example-btn" onClick={() => { setSearchQuery('Backend in Engineering'); handleSearch(); }}>Backend in Engineering</button>
        <button className="example-btn" onClick={() => { setSearchQuery('Data scientists'); handleSearch(); }}>Data scientists</button>
      </div>

      {summary && (
        <div className="summary">
          <strong>Summary:</strong> {summary}
        </div>
      )}

      {employees.length > 0 && (
        <div className="results">
          <h2>Results: <span className="count">{employees.length}</span> found</h2>
          <div className="employees-grid">
            {employees.map((emp, index) => (
              <div key={index} className="employee-card">
                <h3>{emp.name}</h3>
                <p className="email">{emp.email}</p>
                <p className="info">{emp.department} • {emp.years} years • Joined {emp.joinDate}</p>
                <div className="skills">
                  {emp.skills && emp.skills.map((skill, i) => (
                    <span key={i} className="skill-tag">{skill}</span>
                  ))}
                </div>
                <p className="role">{emp.role}</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
