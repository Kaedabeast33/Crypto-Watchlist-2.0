package com.kaeden.watchlist.Controller;

import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Repository.CoinDataRepository;
import com.kaeden.watchlist.Service.CoinDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@RestController
@RequestMapping("/coins")
public class CoinController {
    @Autowired
    private CoinDataService coinService;
    @Autowired
    private CoinDataRepository coinRepository;

    //METHODS
//    @PostMapping("addCoin")
//    public List<String> addCoin(@RequestBody CoinDto coinDto){
//        return coinService.addCoin(coinDto);
//    }
    @GetMapping("/allCoins")
    public List<CoinData> findAllCoins(){
        return coinRepository.findAll();
    }
    @DeleteMapping("/thisshouldnotbedonebyanyoneexcepttheadmin/{coinId}")
    public void deleteCoin(@PathVariable String coinId){
        Optional<CoinData> coinOptional = coinRepository.findById(coinId);
        coinOptional.ifPresent(coin->coinRepository.delete(coin));
    }


}
