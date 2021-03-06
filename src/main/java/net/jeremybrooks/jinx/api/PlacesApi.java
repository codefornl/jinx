/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;

/**
 * Created by jeremyb on 7/31/14.
 */
public class PlacesApi {
    private Jinx jinx;
    private PlacesApi() {}
    public PlacesApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.places.find.html">flickr.places.find</a>

     Return a list of place IDs for a query string.

     The flickr.places.find method is not a geocoder. It will round up to the nearest place type to which place IDs apply. For example, if you pass it a street level address it will return the city that contains the address rather than the street, or building, itself.
     Authentication

     This method does not require authentication.

     Arguments

     api_key (Required)
     Your API application key. See here for more details.
     query (Required)
     The query string to use for place ID lookups
     * @param query
     * @throws JinxException
     */
    public void find(String query) throws JinxException {
        //TODO
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.places.findByLatLon.html">flickr.places.findByLatLon</a>

     Return a place ID for a latitude, longitude and accuracy triple.

     The flickr.places.findByLatLon method is not meant to be a (reverse) geocoder in the traditional sense. It is designed to allow users to find photos for "places" and will round up to the nearest place type to which corresponding place IDs apply.

     For example, if you pass it a street level coordinate it will return the city that contains the point rather than the street, or building, itself.

     It will also truncate latitudes and longitudes to three decimal points.
     Authentication

     This method does not require authentication.

     Arguments

     api_key (Required)
     Your API application key. See here for more details.
     lat (Required)
     The latitude whose valid range is -90 to 90. Anything more than 4 decimal places will be truncated.
     lon (Required)
     The longitude whose valid range is -180 to 180. Anything more than 4 decimal places will be truncated.
     accuracy (Optional)
     Recorded accuracy level of the location information. World level is 1, Country is ~3, Region ~6, City ~11, Street ~16. Current range is 1-16. The default is 16.
     * @param lat
     * @param lon
     * @param accuracy
     * @throws JinxException
     */
    public void findByLatLon(Float lat, Float lon, Integer accuracy) throws JinxException {
        //TODO
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.places.getChildrenWithPhotosPublic.html">flickr.places.getChildrenWithPhotosPublic</a>

     Return a list of locations with public photos that are parented by a Where on Earth (WOE) or Places ID.
     Authentication

     This method does not require authentication.

     Arguments

     api_key (Required)
     Your API application key. See here for more details.
     place_id (Optional)
     A Flickr Places ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
     woe_id (Optional)
     A Where On Earth (WOE) ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
     * @param placeId
     * @param woeId
     * @throws JinxException
     */
    public void getChildrenWithPhotosPublic(String placeId, String woeId) throws JinxException {
        //TODO
    }

}
