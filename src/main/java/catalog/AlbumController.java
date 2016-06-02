/*
 * Copyright 2016 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

/**
 * AlbumController provides a CRUD interface to MongoDB.
 *
 * Route: `/albums`
 */
@RestController
@RequestMapping("/albums")
@EnableAutoConfiguration
public class AlbumController {

    /**
     * Repository used to retrieve and store albums.
     */
    @Autowired
    private AlbumRepository repository;

    /**
     * Runs AlbumRepository as a Spring application.
     *
     * @param args the program arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AlbumController.class, args);
    }

    /**
     * Gets all the Albums.
     *
     * Route: `GET /albums`
     *
     * @return a JSON array containing all the albums
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Album> getAlbums() {
        List<Album> response = new ArrayList<>();
        for (Album album : repository.findAll()) {
            response.add(album);
        }
        return response;
    }

    /**
     * Creates a new Album.
     *
     * Route: `POST /albums`
     *
     * @return a JSON object containing the creation status.
     */
    @RequestMapping(method = RequestMethod.POST)
    Map<String, Object> createAlbum(@RequestBody Map<String, Object> albumMap) {
        // Create Album instance from POST data.
        //FIXME input should be verified and sanitized!
        Album album = new Album(
                albumMap.get("title").toString(),
                albumMap.get("artist").toString(),
                Integer.parseInt(albumMap.get("year").toString()),
                Double.parseDouble(albumMap.get("price").toString()));

        // Save the album in the repository.
        repository.save(album);

        // Build the response.
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Album created successfully");
        response.put("album", album);
        return response;
    }

    /**
     * Gets a specific Album by its ID.
     *
     * Route: `GET /albums/{albumId}`
     *
     * @return a JSON object representing the Album.
     */
    @RequestMapping(value = "/{albumId}", method = RequestMethod.GET)
    Album getAlbum(@PathVariable("albumId") String albumId) {
        return repository.findOne(albumId);
    }

    /**
     * Edits an existing Album.
     *
     * Route: `PUT /albums/{albumId}`
     *
     * @return a JSON object containing the update status.
     */
    @RequestMapping(value = "/{albumId}", method = RequestMethod.PUT)
    Map<String, Object> editAlbum(
            @PathVariable("albumId") String albumId,
            @RequestBody Map<String, Object> albumMap) {
        // Get the instance of the album from database.
        Album album = repository.findOne(albumId);

        // Update Album instance from POST data.
        //FIXME input should be verified and sanitized!
        album.setTitle(albumMap.get("title").toString());
        album.setArtist(albumMap.get("artist").toString());
        album.setYear(Integer.parseInt(albumMap.get("year").toString()));
        album.setPrice(Double.parseDouble(albumMap.get("price").toString()));

        // Save the album in the repository.
        repository.save(album);

        // Build the response.
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Album updated successfully");
        response.put("album", album);
        return response;
    }
}
