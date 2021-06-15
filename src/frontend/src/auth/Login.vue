<template>
    <v-form 
    ref="form" 
    v-model="valid" 
    lazy-validation>
        <v-text-field 
        v-model="usernameOrEmail" 
        :rules="usernameOrEmailRules" 
        label="Username or Email" 
        required>
        </v-text-field>
        <v-text-field 
        v-model="password" 
        :rules="passwordRules" 
        label="Password" 
        required>
        </v-text-field>
        <v-btn
        :disabled="!valid"
        color="success"
        @click="submit()"
        >
        Submit
        </v-btn>
        <v-btn
        color="error"
        @click="reset()"
        >
        Reset
        </v-btn>
    </v-form>
</template>

<script>
export default {
    data: () => ({
        valid: true,
        usernameOrEmail: "",
        password: "",
        usernameOrEmailRules: [ v => !!v || 'usernameOrEmail is required!' ],
        passwordRules: [ v => !!v || 'Password is required!' ]
    }),
    methods: {
        submit() {
            this.$refs.form.validate()
            let usernameOrEmail = this.usernameOrEmail
            let password = this.password
            this.$store.dispatch('login', { usernameOrEmail, password })
            .then(() => this.$router.push({ name: "Category", params: { message: this.$store.state.message } }))
            .catch(e => console.log(e))
        },
        reset() {
            this.$refs.form.reset()
        },
    }
};
</script>