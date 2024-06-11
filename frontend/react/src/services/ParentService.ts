import axios from 'axios';
import Parent from '../model/Parent';

const REST_API_BASE_URL = 'http://localhost:3000/api';

export const fetchParents = async (): Promise<Parent[]> => {
  const response = await axios.get(`${REST_API_BASE_URL}/parents`);
  return response.data;
};

export const fetchParentById = async (id: number): Promise<Parent> => {
  const response = await axios.get(`${REST_API_BASE_URL}/parents/${id}`);
  return response.data;
};

export const updateParent = async (parent: Parent): Promise<void> => {
  const data = {
    name: parent.name,
  };
  await axios.put(`${REST_API_BASE_URL}/parents/${parent.id}`, data);
};

export const createParent = async (parent: Parent): Promise<void> => {
  const data = {
    name: parent.name,
  };
  await axios.post(`${REST_API_BASE_URL}/parents`, data);
};

export const deleteParent = async (id: number): Promise<void> => {
  await axios.delete(`${REST_API_BASE_URL}/parents/${id}`);
};
