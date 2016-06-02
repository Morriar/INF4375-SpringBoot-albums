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

import org.springframework.data.annotation.Id;

/**
 * Album representation that can be stored in an AlbumRepository.
 */
public class Album {

    /**
     * Primary key used by AlbumRepository.
     */
    @Id
    private String id;

    private String title;
    private String artist;
    private Integer year;
    private Double price;

    public Album() {
    }

    /**
     * The constructor does not accept id, we let the repository generate it.
     * 
     * @param title
     * @param artist
     * @param year
     * @param price
     */
    public Album(String title, String artist, Integer year, Double price) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Album[id=%s, title=%s, artist=%s, year=%d, price=%f]",
                id, title, artist, year, price);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
