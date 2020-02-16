package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.entities.Player;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFormData {

  private List<Player> players;
}
