import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { grey} from '@mui/material/colors';
import Navbar from '../components/Navbar';
import {
  Typography,
  Container,
  Button,
  Box,
  InputBase,
  List,
  ListItem,
  ListItemText,
  Chip,
} from '@mui/material';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import debounce from 'lodash.debounce';

function Test2() {
  const navigate = useNavigate();
  const location = useLocation();
  const form = location.state || {};
  const [searchQuery, setSearchQuery] = useState('');
  const [competencies, setCompetencies] = useState([]);
  const [selectedCompetencies, setSelectedCompetencies] = useState([]);

  const fetchCompetencies = debounce((query) => {
    axios.get('http://localhost:8088/api/competencies/search', {
      params: { query, themeId: form.domain, roleId: form.role, levelId: form.level }
    })
    .then(response => {
      setCompetencies(response.data);
    })
    .catch(error => console.error('Error fetching competencies:', error));
  }, 300);

  useEffect(() => {
    if (searchQuery) {
      fetchCompetencies(searchQuery);
    } else {
      setCompetencies([]);
    }
  }, [searchQuery, form]);

  const handleAddCompetency = (competency) => {
    setSelectedCompetencies((prev) => [...prev, competency]);
    setSearchQuery(''); 
  };

  const handleRemoveCompetency = (competencyToRemove) => {
    setSelectedCompetencies((prev) =>
      prev.filter((competency) => competency.id !== competencyToRemove.id)
    );
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (selectedCompetencies.length === 0) {
      alert("Veuillez sélectionner au moins une compétence.");
      return;
    }
    navigate('/Test3', { state: { ...form, selectedCompetencies } });
  };

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
      <Navbar />

      <Typography variant="h2" sx={{ marginTop: '15vh', fontSize: '3.5em', fontStyle: 'italic' }}>
        Créer un test
      </Typography>

      <Container sx={{ backgroundColor: '#fff', padding: 3, borderRadius: 1, boxShadow: '0 2px 4px rgba(0, 0, 0, 0.3)', margin: 3 }}>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column' }}>
          <Typography variant="h2" style={{ fontSize: '1.3em', color: '#000', textAlign: 'Left', paddingBottom: '3%' }}>Quelles compétences souhaitez-vous tester ?</Typography>
          <Box sx={{ display: 'flex', alignItems: 'center', flexWrap: 'wrap', gap: 1, marginBottom: 2 }}>
            {selectedCompetencies.map((competency) => (
              <Chip
                key={competency.id}
                label={competency.name}
                onDelete={() => handleRemoveCompetency(competency)}
                sx={{ marginBottom: '8px' }}
              />
            ))}
          </Box>
          <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '100%', marginBottom: 2, position: 'relative' }}>
            <FontAwesomeIcon icon={faSearch} style={{ position: 'absolute', left: '1%', color: '#aaa', fontSize: '16px' }} />
            <InputBase
              type="text"
              placeholder="Rechercher..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              sx={{
                width: '100%',
                padding: '10px 40px',
                borderRadius: 30,
                border: '1px solid #ccc',
                outline: 'none',
                fontSize: '1em',
              }}
            />
          </Box>
          <List sx={{ maxHeight: '200px', overflowY: 'auto', marginBottom: 2 }}>
            {competencies.map((competency) => (
              <ListItem key={competency.id} button onClick={() => handleAddCompetency(competency)}>
                <ListItemText primary={competency.name} />
              </ListItem>
            ))}
          </List>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
            <Button type="button" variant="contained" onClick={() => navigate('/Test1')} sx={{ backgroundColor: '#232A56', borderRadius: 30, padding: '10px', marginTop: 1, width: '40%', '&:hover': { backgroundColor: grey[400], color: '#232A56' }   }}>
              Retour
            </Button>
            <Button type="submit" variant="contained" sx={{ backgroundColor: '#232A56', borderRadius: 30, padding: '10px', marginTop: 1, width: '40%', '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}>
              Suivant
            </Button>
          </Box>
        </form>
      </Container>
    </Box>
  );
}

export default Test2;
