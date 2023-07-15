
// querySelector 등록 likeCount actionLike 
const likeCountElement = document.querySelector(".likeCount");
const likeButton = document.querySelector(".actionLike");

// Like Path 
const likeLink = "http://localhost:8084/like"


const updateLikeCount = async (nno) => {
    const newCount = await toggleGet(nno);
    likeCountElement.innerText = newCount;
};

document.addEventListener("DOMContentLoaded", async () => {
    await updateLikeCount(nno);
});

likeButton.addEventListener("click", async () => {
    await toggleLike(nno);
    await updateLikeCount(nno);
    // Action Color Change
    toggleLikeButtonColor();
});

// Get Like Read Axios
const toggleGet = async (nno) => {
    console.log("nno",nno)
    const response = await axios.get(`${likeLink}/nno/${nno}/count`);
    return response.data.result;
};

// Count Like Axios
const toggleLike = async (nno) => {
    const response = await axios.post(`${likeLink}/nno/toggle/${nno}`);
    updateLikeCount(nno);
    return response.data;
};

// Action Color Change
const toggleLikeButtonColor = () => {
    likeButton.classList.toggle("liked");
};