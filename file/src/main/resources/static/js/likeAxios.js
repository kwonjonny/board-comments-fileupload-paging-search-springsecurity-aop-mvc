
// Like Axios & JavaScript Code 

// querySelector 등록 likeCount actionLike 
const likeCountElement = document.querySelector(".likeCount");
const likeButton = document.querySelector(".actionLike");

// Like Path 
const likeLink = "http://localhost:8084/like"


const updateLikeCount = async (tno) => {
    const newCount = await toggleGet(tno);
    likeCountElement.innerText = newCount;
};

document.addEventListener("DOMContentLoaded", async () => {
    await updateLikeCount(tno);
});

likeButton.addEventListener("click", async () => {
    await toggleLike(tno);
    await updateLikeCount(tno);
    // Action Color Change
    toggleLikeButtonColor();
});

// Get Like Read Axios
const toggleGet = async (tno) => {
    const response = await axios.get(`${likeLink}/tno/${tno}/count`);
    return response.data.result;
};

// Count Like Axios
const toggleLike = async (tno) => {
    const response = await axios.post(`${likeLink}/tno/toggle/${tno}`);
    updateLikeCount(tno);
    return response.data;
};

// Action Color Change
const toggleLikeButtonColor = () => {
    likeButton.classList.toggle("liked");
};