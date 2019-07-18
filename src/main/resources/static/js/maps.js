$(document).ready(function(){

    $("#wrapper").fadeIn();

    var map;
    var infowindow;
    var service;
    var autocompleteOptions = {
        componentRestrictions: {
            "country": "us"
        }
    };
    var markers = [];
    var acInput = document.getElementById("autocompleteMap");
    var autocomplete = new google.maps.places.Autocomplete(acInput, autocompleteOptions);

    $("#searchForm").on("submit", function(e){
        e.preventDefault();
    });

    getLocation();

    // Bias the autocomplete object to the user's geographical location,
    // as supplied by the browser's 'navigator.geolocation' object.
    function geolocate() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var geolocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var circle = new google.maps.Circle({
                    center: geolocation,
                    radius: position.coords.accuracy
                });
                autocomplete.setBounds(circle.getBounds());
            });
        }
    }

    // Geo Location
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(initMap);
            // $autocomplete.onfocus(geolocate());
        } else {
            x.innerHTML = "Geolocation is not supported by this browser.";
        }
    }

    function initMap(position) {

        $("#wrapper").fadeIn();

        var curLocation = {lat: position.coords.latitude, lng: position.coords.longitude};

        map = new google.maps.Map(document.getElementById('map'), {
            center: curLocation,
            zoom: 17
        });

        infowindow = new google.maps.InfoWindow();

        service = new google.maps.places.PlacesService(map);

        autocomplete.addListener('place_changed', onPlaceChanged);

        search(curLocation);
    }

    function onPlaceChanged() {
        $("#wrapper").fadeIn();
        var place = autocomplete.getPlace();
        if (place.geometry) {
            map.panTo(place.geometry.location);
            map.setZoom(15);
            map.setMyLocationEnabled(true);
            clearMarkers();
            search(place.geometry.location);
        } else {
            // acInput.setPlaceholder("Type the city, address or zip code");
        }
    }

    function search(place) {
        service.nearbySearch({
            location: place,
            radius: 1000,
            type: ['food'],
            keyword: "mexican"
        }, callback);

    }

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            if (markers[i]) {
                markers[i].setMap(null);
            }
        }
        markers = [];
    }

    function callback(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            for (var i = 0; i < results.length; i++) {
                addMarker(results[i]);
            }
        }
        $("#wrapper").fadeOut();
    }

    function addMarker(place) {
        var photos = place.photos;

        if (!photos) {
            return;
        }

        var photo = photos[0].getUrl({'maxWidth': 150, 'maxHeight': 150});
        var content = "<h6>" + place.name + "</h6>" +
            "<br/>" +
            "<img class='center-align center' src='" + photo + "' /><br/>"+
            "<button id='btn-review' class='btn bg-green-flag'>Add review</button><br/>" +
            "<button id='btn-fav' class='btn mexican-pink'>Add to Fav</button>";

        var marker = new google.maps.Marker({
            map: map,
            position: place.geometry.location,
            title: place.name,
            // icon: photos[0].getUrl({'maxWidth': 50, 'maxHeight': 50})
        });
        google.maps.event.addListener(marker, 'click', function() {
            infowindow.setContent(content);
            infowindow.open(map, this);
        });


        markers.push(marker);
    }

    function setMapOnAll(map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }
    }

});