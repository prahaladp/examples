package main

import (
    "fmt"
    "io"
    "io/ioutil"
    "net/http"
)

const apiVersion = "v1"
const basePath = "https://photoslibrary.googleapis.com/"

// PhotosHelper is a client for uploading a media.
// photoslibrary does not provide `/v1/uploads` API so we implement here.
type PhotosHelper struct {
    client *http.Client
}

// NewPhotosHelper creates a new client.
func NewPhotosHelper(client *http.Client) *PhotosHelper {
    return &PhotosHelper{client}
}

// Upload sends the media and returns the UploadToken.
func (c *PhotosHelper) Upload(r io.Reader, filename string) (string, error) {
    req, err := http.NewRequest("POST", fmt.Sprintf("%s/%s/uploads", basePath, apiVersion), r)
    if err != nil {
        return "", err
    }
    req.Header.Add("X-Goog-Upload-File-Name", filename)

    res, err := c.client.Do(req)
    if err != nil {
        return "", err
    }
    defer res.Body.Close()

    b, err := ioutil.ReadAll(res.Body)
    if err != nil {
        return "", err
    }
    uploadToken := string(b)
    return uploadToken, nil
}

func (c *PhotosHelper) listAlbums(r io.Reader) (string, error) {
    //req, err = http.NewRequest("GET", fmt.Sprintf("%s/%s/albums", basePath, apiVersion), r)
    //req, err := http.NewRequest("POST", fmt.Sprintf("%s/%s/uploads", basePath, apiVersion), r)
    req, err := http.NewRequest("GET", fmt.Sprintf("%s/%s/albums", basePath, apiVersion), nil)

    if err != nil {
        return "", err
    }
    res, err := c.client.Do(req)
    if err != nil {
        return "", err
    }
    defer res.Body.Close()

    if res.StatusCode == http.StatusOK {
        bodyBytes, err2 := ioutil.ReadAll(res.Body)
        bodyString := string(bodyBytes)
        fmt.Println("HTTP GET Album response ", bodyString)
        return bodyString, err2

    }
    fmt.Println("not OK %d", res)
    return "", nil
}

func (c *PhotosHelper) listMediaItems(r io.Reader) (string, error) {
    req, err := http.NewRequest("POST", fmt.Sprintf("%s/%s/mediaItems:search", basePath, apiVersion), nil)

    if err != nil {
        return "", err
    }
    res, err := c.client.Do(req)
    if err != nil {
        return "", err
    }
    defer res.Body.Close()

    if res.StatusCode == http.StatusOK {
        bodyBytes, err2 := ioutil.ReadAll(res.Body)
        bodyString := string(bodyBytes)
        fmt.Println("HTTP POST media items response ", bodyString)
        return bodyString, err2
    }
    fmt.Println("not OK %d", res)
    return "", nil
}

