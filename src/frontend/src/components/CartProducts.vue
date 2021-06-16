<template>
  <v-item-group>
    <v-container>
      <v-row>
        <v-col
          v-for="(cartProduct, i) in cartProducts"
          :key="i"
          cols="12"
          md="2"
        >
          <v-card
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                  v-text="cartProduct.name">
                </v-card-title>
                <div align="right">
                  <v-btn icon @click="deleteCartProduct(cartProduct.productId)">
                    <v-icon color="red">mdi-trash-can-outline</v-icon>
                  </v-btn>
                </div>
                <v-card-text>
                  Price: {{ cartProduct.price }}
                  <br>
                  Amount: {{ cartProduct.amount }}
                </v-card-text>
                <v-card-actions>
                  <v-text-field
                    v-model="amount"
                    label="Amount"
                  ></v-text-field>
                  <v-btn color="success" @click="updateCartProduct(cartProduct.productId, amount)">update</v-btn>
                </v-card-actions>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-item-group>
</template>

<script>
export default {
    data: () => ({
      cartProducts: [],
      amount: null
    }),
    computed: {
        cartId() {
            return this.$route.params.cartId;
        }
    },
    methods: {
        getCartProducts() {
            this.$http.get(`/api/carts/${this.cartId}`).then(response => {
                this.cartProducts = response.data;
            })
        },
        updateCartProduct(productId, a) {
          this.cartId *= 1;
          productId *= 1;
          a *= 1;
          this.$http.put(`api/carts/${this.cartId}/products/${productId}`, {
            amount:  a
          }).then(() => {
            this.getCartProducts();
          })
        },

        deleteCartProduct(productId) {
          productId *= 1;
          this.$http.delete(`api/carts/${this.cartId}/products/${productId}`)
          .then(() => {
            this.getCartProducts();
          })
        }
    },
    created() {
        this.getCartProducts();
    }
}
</script>