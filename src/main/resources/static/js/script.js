// Define an array of blog post data
const blogPosts = [
    {
        title: 'My First Blog Post',
        imageUrl: 'https://placeimg.com/640/480/tech',
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse luctus leo vel felis vulputate, at posuere odio lobortis. Fusce eget enim vel mauris imperdiet malesuada. Nam viverra, ante nec vulputate sagittis, eros tortor interdum massa, et tincidunt tellus sapien eu turpis.'
    },
    {
        title: 'My Second Blog Post',
        imageUrl: 'https://placeimg.com/640/480/nature',
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse luctus leo vel felis vulputate, at posuere odio lobortis. Fusce eget enim vel mauris imperdiet malesuada. Nam viverra, ante nec vulputate sagittis, eros tortor interdum massa, et tincidunt tellus sapien eu turpis.'
    },
    {
        title: 'My Third Blog Post',
        imageUrl: 'https://placeimg.com/640/480/animals',
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse luctus leo vel felis vulputate, at posuere odio lobortis. Fusce eget enim vel mauris imperdiet malesuada. Nam viverra, ante nec vulputate sagittis, eros tortor interdum massa, et tincidunt tellus sapien eu turpis.'
    }
];

// Get the element where the blog post HTML will be inserted
const blogPostContainer = document.querySelector('#blog-posts');

// Loop through the blog post data array and create HTML elements for each post
blogPosts.forEach(post => {
    // Create HTML elements for the blog post
    const article = document.createElement('article');
    const heading = document.createElement('h2');
    const image = document.createElement('img');
    const text = document.createElement('p');

    // Set the content and attributes for the HTML elements
    heading.textContent = post.title;
    image.src = post.imageUrl;
    image.alt = `Picture of ${post.title.toLowerCase().split(' ')[0]}`;
    text.textContent = post.text;

    // Append the HTML elements to the article element
    article.appendChild(heading);
    article.appendChild(image);
    article.appendChild(text);

    // Append the article element to the blog post container
    blogPostContainer.appendChild(article);
});

// Show a confirmation message when the user clicks on a link
const links = document.querySelectorAll('a');
links.forEach(link => {
    link.addEventListener('click', e => {
        if (!confirm('Are you sure you want to leave this page?')) {
            e.preventDefault();
        }
    });
});

// Display the current date in the footer
const currentDate = new Date();
const year = currentDate.getFullYear();
const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
const day = ('0' + currentDate.getDate()).slice(-2);
const footerDate = document.querySelector('footer p');
footerDate.textContent = `&copy; ${year} My Blog. All rights reserved. Last updated: ${month}/${day}/${year}`;