import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';


export class InMemoryDataService implements InMemoryDbService {

    createDb(){
        let movies = [
            {
  "_embedded" : {
    "movies" : [ {
      "description" : "Strasne dobry film scify horror komedia drama",
      "categories" : [ {
        "name" : "Horror",
        "new" : false
      } ],
      "titles" : [ {
        "language" : "SK",
        "name" : "Alf"
      }, {
        "language" : "EN",
        "name" : "Alf"
      } ],
      "id" : 0,
      "_links" : {
        "self" : {
          "href" : "http://localhost/api/movies/0"
        },
        "movie" : {
          "href" : "http://localhost/api/movies/0{?projection}",
          "templated" : true
        },
        "categories" : {
          "href" : "http://localhost/api/movies/0/categories"
        }
      }
    }, {
      "description" : "No neviem dobry film scify horror komedia drama",
      "categories" : [ {
        "name" : "Horror",
        "new" : false
      } ],
      "titles" : [ {
        "language" : "SK",
        "name" : "Akta X"
      }, {
        "language" : "EN",
        "name" : "The X Files"
      } ],
      "id" : 1,
      "_links" : {
        "self" : {
          "href" : "http://localhost/api/movies/1"
        },
        "movie" : {
          "href" : "http://localhost/api/movies/1{?projection}",
          "templated" : true
        },
        "categories" : {
          "href" : "http://localhost/api/movies/1/categories"
        }
      }
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost/api/movies/search/findByTitle?page=0&size=2"
    },
    "self" : {
      "href" : "http://localhost/api/movies/search/findByTitle"
    },
    "next" : {
      "href" : "http://localhost/api/movies/search/findByTitle?page=1&size=2"
    },
    "last" : {
      "href" : "http://localhost/api/movies/search/findByTitle?page=3&size=2"
    }
  },
  "page" : {
    "size" : 2,
    "totalElements" : 8,
    "totalPages" : 4,
    "number" : 0
  }
}
        ]

        return {movies};
    }
}