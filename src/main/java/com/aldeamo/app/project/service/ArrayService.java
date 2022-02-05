package com.aldeamo.app.project.service;

import com.aldeamo.app.project.repository.ArrayRepository;
import com.aldeamo.app.project.domain.Array;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArrayService {

    private ArrayRepository arrayRepository;

    public ArrayService(ArrayRepository arrayRepository){
        this.arrayRepository = arrayRepository;
    }

    public List<Integer> getAnswerArray(int iterationAmount, Long arrayID){
        Optional<Array> array = arrayRepository.findById(arrayID);

        List<Integer> answer = new ArrayList<>();

        if(array.isPresent()){
            List<Integer> cups = Arrays.stream(array.get()
                    .getInputArray()
                    .split(",")).map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Integer> primes = new ArrayList<>();
            primes.add(2);

            List<Integer> primeDividends = new ArrayList<>();
            List<List<Integer>> notPrimeDividends = new ArrayList<>();
            notPrimeDividends.add(cups);

            for(int i = 0; i < iterationAmount; i++){
                primeDividends.clear();

                if(notPrimeDividends.get(i).size() <= 1){
                    break;
                }

                notPrimeDividends.add(new ArrayList<>());
                classifyCups(notPrimeDividends, i, primeDividends, primes);
                primes.add(findNextPrime(primes.get(i) + 1));
                answer.addAll(primeDividends);

            }
            answer.addAll(notPrimeDividends.get(notPrimeDividends.size() - 1));
        }

        return answer;
    }


    public void classifyCups(List<List<Integer>> notPrimeDividends, int i, List<Integer> primeDividends, List<Integer> primes){
        List<Integer> numbersToClassify = notPrimeDividends.get(i);

        for(int j = numbersToClassify.size() - 1; j > -1; j--){

            if(numbersToClassify.get(j) % primes.get(i) == 0){
                primeDividends.add(numbersToClassify.get(j));
            }
            else{
                notPrimeDividends.get(i + 1).add(numbersToClassify.get(j));
            }

        }
    }

    public int findNextPrime(int startingPrime) {
        int counter = 0;
        int isPrime = startingPrime;

        while (counter == 0) {
            boolean prime = true;
            for (int j = 2; j < isPrime; j++) {
                if (isPrime % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                counter++;
                break;
            }
            isPrime++;
        }

        return isPrime;
    }

}
