docker build -t url-shortener .
docker run -d -p 8080:8080 --name url-shortener url-shortener
docker logs -f url-shortener
