const apiUrl = 'http://localhost:8080/api/anime';

document.getElementById('showFormButton').addEventListener('click', () => {
    document.getElementById('animeForm').classList.toggle('hidden');
});

// Function to fetch and display anime data
async function fetchAnimeData() {
    const response = await fetch(apiUrl);
    const animeList = await response.json();

    const animeListElement = document.getElementById('animeList');
    animeListElement.innerHTML = '';

    animeList.forEach(anime => {
        const animeCard = document.createElement('div');
        animeCard.classList.add('anime-card');
        animeCard.innerHTML = `
            <img src="${anime.imageURL}" alt="${anime.title}">
            <h3>${anime.title}</h3>
            <p>${anime.genre}</p>
            <button class="delete-button" onclick="deleteAnime(${anime.id})">Delete</button>
        `;
        animeListElement.appendChild(animeCard);
    });
}

// Function to add a new anime entry
async function addAnime(event) {
    event.preventDefault();

    const title = document.getElementById('title').value;
    const genre = document.getElementById('genre').value;
    const imageURL = document.getElementById('imageURL').value;

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, genre, imageURL }),
    });

    if (response.status === 201) {
        fetchAnimeData();
        document.getElementById('animeForm').classList.add('hidden');
    }

    document.getElementById('title').value = '';
    document.getElementById('genre').value = '';
    document.getElementById('imageURL').value = '';
}

// Function to delete an anime entry
async function deleteAnime(id) {
    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'DELETE',
    });

    if (response.status === 204) {
        fetchAnimeData();
    }
}

// Add event listeners
document.getElementById('addAnimeForm').addEventListener('submit', addAnime);

// Initial data load
 fetchAnimeData