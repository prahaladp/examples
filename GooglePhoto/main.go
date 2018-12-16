package main

/*
 * derived from https://github.com/int128/hello-google-photos/blob/master/main.go
 */

import (
    "context"
    "log"
    "os"
    photoslibrary "google.golang.org/api/photoslibrary/v1"
)

func main() {
    clientID := os.Getenv("GOOGLE_CLIENT_ID")
    clientSecret := os.Getenv("GOOGLE_CLIENT_SECRET")
    if clientID == "" || clientSecret == "" {
        log.Fatal(`Error: GOOGLE_CLIENT_ID and GOOGLE_CLIENT_SECRET must be set.
1. Open https://console.cloud.google.com/apis/credentials
2. Create an OAuth client ID where the application type is other.
3. Set the following environment variables:
export GOOGLE_CLIENT_ID=
export GOOGLE_CLIENT_SECRET=
`)
    }

    ctx := context.Background()
    client, err := NewOAuthClient(ctx, clientID, clientSecret)
    if err != nil {
        log.Fatal(err)
    }

    helper := NewPhotosHelper(client)
    photos, err := photoslibrary.New(client)
    if err != nil {
        log.Fatal(err)
    }

    listAlbums(helper, photos)
    listMediaItems(helper, photos)
    for _, filepath := range os.Args[1:] {
        upload(filepath, helper, photos)
    }
}

func listAlbums(helper *PhotosHelper, photos *photoslibrary.Service) {
    helper.listAlbums(nil)
}

func listMediaItems(helper *PhotosHelper, photos *photoslibrary.Service) {
    helper.listMediaItems(nil)
}


func upload(filepath string, helper *PhotosHelper, photos *photoslibrary.Service) {
}
