import React, { useState, useEffect } from 'react';
import { TextField, Typography, Select, MenuItem, FormControl, InputLabel, Container, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Navbar from '../components/Navbar';

function Test1() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    adminEmail: '',
    domain: '',
    role: '',
    level: '',
  });

  const [domains, setDomains] = useState([]);
  const [roles, setRoles] = useState([]);
  const [levels, setLevels] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8088/api/themes')
      .then(response => {
        if (Array.isArray(response.data)) {
          setDomains(response.data);
        } else {
          console.error("La réponse de l'API n'est pas un tableau", response.data);
        }
      })
      .catch(error => console.error("Error fetching data:", error));
  }, []);

  useEffect(() => {
    if (form.domain) {
      axios.get(`http://localhost:8088/api/roles/${form.domain}`)
        .then(response => {
          setRoles(response.data);
        })
        .catch(error => console.error("Error fetching roles:", error));

      axios.get(`http://localhost:8088/api/levels/${form.domain}`)
        .then(response => {
          setLevels(response.data);
        })
        .catch(error => console.error("Error fetching levels:", error));
    } else {
      setRoles([]);
      setLevels([]);
    }
  }, [form.domain]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (form.adminEmail && form.domain && form.role && form.level) {
      setError('');
      navigate('/Test2', { state: form });
    } else {
      setError("All fields are required");
    }
  };

  const isFormComplete = form.adminEmail && form.domain && form.role && form.level;

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
        background: 'linear-gradient(to bottom, #f0f0f0 65%, #232A56 35%)',
        color: '#000',
        overflowX: 'hidden',
      }}
    >
      <Navbar/>

      <Typography variant="h2" sx={{ marginTop: '15vh', fontSize: '3.5em', fontStyle: 'italic' }}>
        Créer un test
      </Typography>

      <Container sx={{ backgroundColor: '#fff', padding: 3, borderRadius: 1, boxShadow: '0 2px 4px rgba(0, 0, 0, 0.3)', margin: 3 }}>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column' }}>
          {error && <Typography color="error">{error}</Typography>}
          <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
            <TextField
              name="adminEmail"
              placeholder="Entrer votre email"
              value={form.adminEmail}
              onChange={handleChange}
              variant="outlined"
              sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}
            />
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le domaine</InputLabel>
              <Select
                name="domain"
                value={form.domain}
                onChange={handleChange}
                label="Choisir le domaine"
              >
                <MenuItem value="">
                  <em>Choisir le domaine</em>
                </MenuItem>
                {domains.map(domain => (
                  <MenuItem key={domain.id} value={domain.id}>{domain.name}</MenuItem>
                ))}
              </Select>
            </FormControl>
          </Box>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le rôle</InputLabel>
              <Select
                name="role"
                value={form.role}
                onChange={handleChange}
                label="Choisir le rôle"
                disabled={!form.domain}
              >
                <MenuItem value="">
                  <em>Choisir le rôle</em>
                </MenuItem>
                {roles.map(role => (
                  <MenuItem key={role.id} value={role.id}>{role.name}</MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le niveau</InputLabel>
              <Select
                name="level"
                value={form.level}
                onChange={handleChange}
                label="Choisir le niveau"
                disabled={!form.domain}
              >
                <MenuItem value="">
                  <em>Choisir le niveau</em>
                </MenuItem>
                {levels.map(level => (
                  <MenuItem key={level.id} value={level.id}>{level.name}</MenuItem>
                ))}
              </Select>
            </FormControl>
          </Box>
          <Button
            type="submit"
            variant="contained"
            disabled={!isFormComplete}
            sx={{
              width: '40%',
              backgroundColor: isFormComplete ? '#232A56' : '#aaa',
              color: '#fff',
              cursor: 'pointer',
              borderRadius: 30,
              margin: '0 auto',
              '&:hover': {
                backgroundColor: isFormComplete ?'#C0C0C0' : '#aaa', color: '#232A56'
              },
            }}
          >
            Suivant
          </Button>
        </form>
      </Container>
    </Box>
  );
}

export default Test1;
