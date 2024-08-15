package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Spotify_Api {
    String token = "BQCVMsvdgXyq0F_aZlhJ5WS682rgTkttWHtnLhZdU2U1IfInwv0AIeZ9kxDzZs85-upNzevGCaOCYXA3JM_FLVNO0JiZ3cShHRInLOAYJRp8LpIO4RvQ13fz1lv-Ie_1PXMmAho_fKtDtk7FhDqxwALu2TLUWf1C8c80klyudIxacvBJ2SrI66vEauK5c4cmlT2SQFNcEYWJ143eSWs8aI0W8VJB7ex4kumJjxfwWzDhVwXvsG9AhBL3dpQak3xPlsXC_VK8Fc4twuNk-32ekFgd8txB0yKbSHGuJeMe-j5_kF4g3oFx_P9Zb40JZoMy4kt9PGIKHP3EtkrxSrJY";

    String userId = null;
    String snapShotId = null;

    @Test
    public void getCurrentUsersProfile(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        userId = response.path("id");
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersTopItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/top/artists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersProfile(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId);
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void followPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/0H0SL89LqDi6gSvJxDQfNR/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void unfollowPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/1PKYiQbbX3Fak5c9SiYpFQ/followers");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getFollowedArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/following?type=artist&after=4YRxDV8wJFPHPTeXepOstw");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void followArtistsOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"0GF4shudTAFv8ak9eWdd4Y\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist&ids=0GF4shudTAFv8ak9eWdd4Y");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test
    public void unfollowArtistsOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"4YRxDV8wJFPHPTeXepOstw\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/following?type=artist&ids=4YRxDV8wJFPHPTeXepOstw");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test
    public void checkIfUserFollowArtistOrUsers(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=4YRxDV8wJFPHPTeXepOstw");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void checkIfCurrentUserFollowsPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/playlists/0H0SL89LqDi6gSvJxDQfNR/followers/contains");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getTrack(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/tracks/7eQl3Yqv35ioqUfveKHitE");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getSeveralTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=7eQl3Yqv35ioqUfveKHitE");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 1)
    public void saveTracksForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"7eQl3Yqv35ioqUfveKHitE\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/tracks?ids=7eQl3Yqv35ioqUfveKHitE");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2)
    public void removeUsersSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"7eQl3Yqv35ioqUfveKHitE\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/tracks?ids=7eQl3Yqv35ioqUfveKHitE");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void checkUsersSavedTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=3eR23VReFzcdmS7TYCrhCe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getSeveralTracksAudioFeatures(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=3eR23VReFzcdmS7TYCrhCe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getTracksAudioFeatures(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/3eR23VReFzcdmS7TYCrhCe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getTracksAudioAnalysis(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/3eR23VReFzcdmS7TYCrhCe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getRecommendations(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getShow(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getShowEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersSavedShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 1)
    public void saveShowsForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 2)
    public void removeUsersSavedShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void checkUsersSavedShows(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains?ids=5CfCWKI5pZ28U0uOzXkDHe");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void searchForItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/search?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=album");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/playlists/0H0SL89LqDi6gSvJxDQfNR");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void changePlaylistDetails(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"name\": \"Songs\",\n" +
                        "    \"description\": \"Favourite Songs\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/5tOZS4myzZkzHk00VWWuNM");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getPlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/playlists/5tOZS4myzZkzHk00VWWuNM");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updatePlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"range_start\": 1,\n" +
                        "    \"insert_before\": 3,\n" +
                        "    \"range_length\": 2\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/5tOZS4myzZkzHk00VWWuNM/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 1)
    public void addItemsToPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:26b3oVLrRUaaybJulow9kz\"\n" +
                        "    ],\n" +
                        "    \"position\": 0\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/5tOZS4myzZkzHk00VWWuNM/tracks");
        response.prettyPrint();
        snapShotId = response.path("snapshot_id");
        response.then().statusCode(201);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 2)
    public void removePlaylistItems(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"spotify:track:26b3oVLrRUaaybJulow9kz\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \""+snapShotId+"\"\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/5tOZS4myzZkzHk00VWWuNM/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getCurrentUsersPlaylists(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersPlaylists(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void createPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"name\": \"NewPlaylist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getFeaturedPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getCategorysPlaylist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getPlaylistCoverImage(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/images");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void addCustomPlaylistCoverImage(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("/9j/2wCEABoZGSccJz4lJT5CLy8vQkc9Ozs9R0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0cBHCcnMyYzPSYmPUc9Mj1HR0dEREdHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR//dAAQAAf/uAA5BZG9iZQBkwAAAAAH/wAARCAABAAEDACIAAREBAhEB/8QASwABAQAAAAAAAAAAAAAAAAAAAAYBAQAAAAAAAAAAAAAAAAAAAAAQAQAAAAAAAAAAAAAAAAAAAAARAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwAAARECEQA/AJgAH//Z")
                .when()
                .put("https://api.spotify.com/v1/playlists/125h9dM54W6SxJWPAwQMZD/images");
        response.prettyPrint();
//        response.then().statusCode(202);
//        Assert.assertEquals(response.statusCode(), 202);
    }

    @Test
    public void getAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/albums/4qApTp9557qYZzRLEih4uP");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralAlbum(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/albums?ids=4qApTp9557qYZzRLEih4uP");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAlbumTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/albums/4qApTp9557qYZzRLEih4uP/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getUsersSavedAlbums(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 1)
    public void saveAlbumsForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"4qApTp9557qYZzRLEih4uP\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/albums?ids=4qApTp9557qYZzRLEih4uP");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 2)
    public void removeUsersSavedAlbums(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"4qApTp9557qYZzRLEih4uP\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/albums?ids=4qApTp9557qYZzRLEih4uP");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void checkUsersSavedAlbums(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/albums/contains?ids=4qApTp9557qYZzRLEih4uP");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getNewReleases(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getArtist(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/artists/4YRxDV8wJFPHPTeXepOstw");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getSeveralArtists(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=4YRxDV8wJFPHPTeXepOstw");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getArtistsAlbums(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/artists/4YRxDV8wJFPHPTeXepOstw/albums");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getArtistsTopTracks(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/artists/4YRxDV8wJFPHPTeXepOstw/top-tracks");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getArtistsRelatedArtists(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/artists/4YRxDV8wJFPHPTeXepOstw/related-artists");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAnAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAudiobookChapters(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe/chapters");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getUsersSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void saveAudiobooksForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void removeUsersSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void checkUsersSavedAudiobook(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks/contains?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralBrowseCategories(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSingleBrowseCategories(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAChapter(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralChapters(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/chapters?ids=0IsXVP0JmcB2adSE338GkK%2C3ZXb8FKZGU0EHALYX6uCzU%2C0D5wENdkdwbqlrHoaJ9g29");
        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getEpisode(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/episodes/0srjCaQdoQCgFUkacWZCzp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getSeveralEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids=0srjCaQdoQCgFUkacWZCzp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getUsersSavedEpisodes(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 1)
    public void saveEpisodesForCurrentUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"0srjCaQdoQCgFUkacWZCzp\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/episodes?ids=0srjCaQdoQCgFUkacWZCzp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void removeUsersSavedEpisode(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"0srjCaQdoQCgFUkacWZCzp\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/episodes?ids=0srjCaQdoQCgFUkacWZCzp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void checkUsersSavedEpisode(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains?ids=0srjCaQdoQCgFUkacWZCzp");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    public void getAvailableGenreSeeds(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAvailableMarkets(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getPlaybackState(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(), 204);
    }

// it requires premium spotify
//    @Test
//    public void transferPlayback(){
//        Response response = given()
//                .header("accept","application/json")
//                .header("Content-Type","application/json")
//                .header("Authorization", "Bearer "+token)
//                .body("{\n" +
//                        "    \"device_ids\": [\n" +
//                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
//                        "    ]\n" +
//                        "}")
//                .when()
//                .get("https://api.spotify.com/v1/me/player");
//        response.prettyPrint();
//        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), 200);
//    }


    @Test
    public void getAvailableDevices(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getCurrentlyPlayingTrack(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");
        response.prettyPrint();
        response.then().statusCode(204);
        Assert.assertEquals(response.statusCode(), 204);
    }

}
