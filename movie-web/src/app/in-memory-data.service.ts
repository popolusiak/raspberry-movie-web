import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';


export class InMemoryDataService implements InMemoryDbService {

    createDb(){
        let movies = [
            {
  "_embedded" : {
    "movies" : [ {
      "description" : "Strasne dobry film scify horror komedia drama",
      "titles" : [ {
        "language" : "SK",
        "name" : "Alf"
      }, {
        "language" : "EN",
        "name" : "Alf"
      } ],
      "categories" : [ {
        "name" : "Horror",
        "new" : false
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
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost/api/movies/search/findByTitle"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
        ]

        return {movies};
    }
}