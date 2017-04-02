import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';


export class InMemoryDataService implements InMemoryDbService {

    createDb(){
        let movies = [
            {id: 1, titles: [{langulage: 'SK', title: 'Nepodarena Baba'},{langulage: 'EN', title: 'Nicht gut frajarka'}], genres: [{id: 1, name: "Horror"},{id: 2, name: "Drama"}], description: 'Peklo na zemi sa uskutocnilo co prisla zloba s temnout a vstupila do zivota nevinnemu chlapcovi Malemu Panovi Marekovi' }
        ]

        return {movies};
    }
}