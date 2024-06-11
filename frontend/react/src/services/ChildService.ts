import axios from 'axios';
import Child from '../model/Child';

const REST_API_BASE_URL = 'http://localhost:3000/api';

export const fetchChildrenByPID = async (pId: number): Promise<Child[]> => {
  const response = await axios.get(`${REST_API_BASE_URL}/parents/${pId}/children`);
  return response.data;
};

export const createChild = async (child: Child): Promise<void> => {
  const data = {
    name: child.name,
    pId: child.pId,
  };
  await axios.post(`${REST_API_BASE_URL}/children`, data);
};

export const fetchChildById = async (id: number): Promise<Child> => {
  const response = await axios.get(`${REST_API_BASE_URL}/children/${id}`);
  return response.data;
};

export const updateChild = async (child: Child): Promise<void> => {
  const data = {
    name: child.name,
  };
  await axios.put(`${REST_API_BASE_URL}/children/${child.id}`, data);
};

export const deleteChild = async (id: number): Promise<void> => {
  await axios.delete(`${REST_API_BASE_URL}/children/${id}`);
};

export const fetchChildren = async (): Promise<Child[]> => {
  const response = await axios.get(`${REST_API_BASE_URL}/children`);
  return response.data;
};
