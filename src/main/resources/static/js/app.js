document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            })

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary

            if (this.currentStep == 5) {


                let quantity = document.querySelector("div[data-step='2']").querySelector("input");
                let organization = document.querySelector("div[data-step='3']").querySelector("input");
                let addressInputs = document.getElementById("addressData").querySelectorAll("input");
                let termInputs = document.getElementById("termData").querySelectorAll("input");
                let comment = document.getElementById("termData").querySelector("textarea");

                let addressData = document.getElementById('addressData-ul');
                let termData = document.getElementById('termData-ul');
                let bagsData = document.getElementById('bagsData');
                let institutionData = document.getElementById('institutionData');

                if (quantity.value == 1) {
                    bagsData.innerText = quantity.value + ' worek ubrań w dobrym stanie dla dzieci';
                } else if (quantity.value >= 2 && quantity.value <= 4) {
                    bagsData.innerText = quantity.value + ' worki ubrań w dobrym stanie dla dzieci';
                } else if ((quantity.value == 0 || quantity.value > 4)){
                    bagsData.innerText = quantity.value + ' worków ubrań w dobrym stanie dla dzieci';
                }

                    institutionData.innerText = 'Dla fundacji "' + institutionData.value + '" w Warszawie';

                addressData.innerText = '';
                termData.innerText = '';

                for (let i = 0; i < 4; i++) {
                    let newLi = document.createElement("li");
                    newLi.innerText = addressInputs[i].value;
                    addressData.appendChild(newLi);
                }

                for (let i = 0; i < 2; i++) {
                    let newLi = document.createElement("li");
                    newLi.innerText = termInputs[i].value;
                    termData.appendChild(newLi);
                }


                let newLi = document.createElement("li");
                if (comment.value !== "") {
                    newLi.innerText = comment.value;
                } else {
                    newLi.innerText = 'Brak uwag';
                }
                termData.appendChild(newLi);
            }

        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});
