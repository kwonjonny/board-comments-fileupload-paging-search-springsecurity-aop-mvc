const createLike = async (likeData) => {

      const response = await axios.post(`${likeLink}/create`, likeData);
      return response.data;
  };
  
const deleteLike = async (likeData) => {
    
    const response = await axios.delete(`${likeLink}/delete`, likeData);
    return response.data;
}

const countLike = async (tno) => {
    
    const response = await axios.get(`${likeLink}${tno}`, tno);
    return response.data;
}